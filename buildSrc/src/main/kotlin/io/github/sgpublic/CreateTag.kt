package io.github.sgpublic

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class CreateTag: DefaultTask() {
    override fun getGroup(): String {
        return "publishing"
    }

    @get:Input
    abstract val token: Property<String>

    @TaskAction
    fun execute() {
        Git.open(project.rootDir).use { git ->
            git.tag()
                .setName("test")
                .call()
            git.push()
                .also {
                    token.orNull?.let { token ->
                        it.setCredentialsProvider(UsernamePasswordCredentialsProvider("mhmzx", token))
                    }
                }
                .setPushAll().setPushTags().call()
        }
    }
}