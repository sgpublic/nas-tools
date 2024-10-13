/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * To learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.5/samples
 */
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.DockerPushImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import com.github.breadmoirai.githubreleaseplugin.GithubReleaseTask
import io.github.sgpublic.*

plugins {
    alias(libs.plugins.docker.api)
    alias(libs.plugins.release.github)
    alias(libs.plugins.buildsrc.utils)
}

group = "io.github.sgpublic"
version = "3.5.0"

tasks {
    val tag = "mhmzx/nas-tools"

    val createTag by creating(CreateTag::class) {
        token = findEnv("publishing.gitlab.token")
    }

    val installXunlei by creating {
        group = "nas-tools"
        doLast {
            delete(
                "./src/main/nas-tools/app/third_party_nas_xunlei",
                "./src/main/nas-tools/app/app/downloader/client/nasxunlei.py",
            )
            copy {
                from("./src/main/xunlei/third_party")
                into("./src/main/nas-tools/app/third_party_nas_xunlei")
            }
            copy {
                from("./src/main/xunlei/nasxunlei.py")
                into("./src/main/nas-tools/app/app/downloader/client")
            }
        }
    }

    val genSites by creating {
        group = "nas-tools"
        doFirst {
            exec {
                workingDir(File(projectDir, "src/main/nas-tools-site"))
                commandLine("python3", "nastools_generate.py")
            }
        }
    }
    val installUserSite by creating {
        group = "nas-tools"
        dependsOn(genSites)
        doLast {
            delete("./src/main/nas-tools/app/web/backend/user.sites.bin")
            copy {
                from("./src/main/nas-tools-site/nas-tools/user.sites.bin")
                into("./src/main/nas-tools/app/web/backend")
            }
        }
    }

    val dockerCreateDockerfile by creating(Dockerfile::class) {
        dependsOn(installXunlei, installUserSite)
        doFirst {
            delete(layout.buildDirectory.file("nas-tools"))
            copy {
                from("./src/main/nas-tools")
                into(layout.buildDirectory.dir("nas-tools/rootf"))
            }
        }
        group = "docker"
        destFile = layout.buildDirectory.file("nas-tools/Dockerfile")
        from("mhmzx/poetry-runner:3.10-bullseye")
        runCommand(command(
            "apt-get update",
            aptInstall(
                "apt-transport-https",
                "apt-utils",
                "bash",
                "ca-certificates",
                "chromium",
                "chromium-driver",
                "curl",
                "dumb-init",
                "ffmpeg",
                "fuse3",
                "git",
                "inotify-tools",
                "locales",
                "musl-dev",
                "netcat",
                "procps",
                "redis",
                "sudo",
                "tzdata",
                "wget",
                "xvfb",
                "zip",
            ),
        ))
        runCommand(command(
            "ln -sf /command/with-contenv /usr/bin/with-contenv",
            "ln -sf /usr/bin/chromedriver /usr/lib/chromium/chromedriver",
        ))
        copyFile("./rootf", "/")
        environmentVariable(mapOf(
            "TERM" to "xtern",
            "TZ" to "Asia/Shanghai",
            "NASTOOL_CONFIG" to "/config/config.yaml",
            "NASTOOL_AUTO_UPDATE" to "false",
            "NASTOOL_CN_UPDATE" to "true",
            "NASTOOL_VERSION" to "master",
            "NASTOOL_VERSION" to "master",
            "REPO_URL" to "https://github.com/sgpublic/nas-tools.git",
            "PYPI_MIRROR" to "https://pypi.tuna.tsinghua.edu.cn/simple",
            "PYTHONWARNINGS" to "ignore:semaphore_tracker:UserWarning",
            "AUTO_VENV" to "1",
            "AUTO_VENV_NAME" to "nas-tools",
            "AUTO_PIP_INSTALL" to "1",
        ))
        workingDir("/app")
    }
    val dockerBuildImage by creating(DockerBuildImage::class) {
        group = "docker"
        dependsOn(dockerCreateDockerfile)
        inputDir = layout.buildDirectory.dir("nas-tools")
        dockerFile = dockerCreateDockerfile.destFile
        images.add(provider { "$tag:v$version" })
        images.add(provider { "$tag:latest" })
    }

    val dockerPushImageOfficial by creating(DockerPushImage::class) {
        group = "docker"
        dependsOn(dockerBuildImage)
        images.add(provider { "$tag:v$version" })
        images.add(provider { "$tag:latest" })
    }

    val githubRelease by getting(GithubReleaseTask::class) {
        authorization = provider {
            "Token ${findEnv("publishing.github.token").get()}"
        }
        owner = "sgpublic"
        repo = "nas-tools"
        tagName = provider { "v$version" }
        releaseName = provider { "v$version" }
        overwrite = true
    }

    val clean by creating(Delete::class) {
        delete(rootProject.file("build"))
    }
}

fun findEnv(name: String) = provider {
    findProperty(name)?.toString()?.takeIf { it.isNotBlank() }
        ?: System.getenv(name.replace(".", "_").uppercase())
}

docker {
    registryCredentials {
        username = findEnv("publishing.docker.username")
        password = findEnv("publishing.docker.password")
    }
}
