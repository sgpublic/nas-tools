package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final DownloadLibraryAccessors laccForDownloadLibraryAccessors = new DownloadLibraryAccessors(owner);
    private final JgitLibraryAccessors laccForJgitLibraryAccessors = new JgitLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for gson (com.google.code.gson:gson)
     * with versionRef 'gson'.
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGson() {
            return create("gson");
    }

    /**
     * Returns the group of libraries at download
     */
    public DownloadLibraryAccessors getDownload() {
        return laccForDownloadLibraryAccessors;
    }

    /**
     * Returns the group of libraries at jgit
     */
    public JgitLibraryAccessors getJgit() {
        return laccForJgitLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlin
     */
    public KotlinLibraryAccessors getKotlin() {
        return laccForKotlinLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class DownloadLibraryAccessors extends SubDependencyFactory {
        private final DownloadGradleLibraryAccessors laccForDownloadGradleLibraryAccessors = new DownloadGradleLibraryAccessors(owner);

        public DownloadLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at download.gradle
         */
        public DownloadGradleLibraryAccessors getGradle() {
            return laccForDownloadGradleLibraryAccessors;
        }

    }

    public static class DownloadGradleLibraryAccessors extends SubDependencyFactory {

        public DownloadGradleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for plugin (de.undercouch.download:de.undercouch.download.gradle.plugin)
         * with versionRef 'download'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPlugin() {
                return create("download.gradle.plugin");
        }

    }

    public static class JgitLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public JgitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jgit (org.eclipse.jgit:org.eclipse.jgit)
         * with versionRef 'jgit'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("jgit");
        }

            /**
             * Creates a dependency provider for apache (org.eclipse.jgit:org.eclipse.jgit.ssh.apache)
         * with versionRef 'jgit'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApache() {
                return create("jgit.apache");
        }

    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {
        private final KotlinGradleLibraryAccessors laccForKotlinGradleLibraryAccessors = new KotlinGradleLibraryAccessors(owner);

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at kotlin.gradle
         */
        public KotlinGradleLibraryAccessors getGradle() {
            return laccForKotlinGradleLibraryAccessors;
        }

    }

    public static class KotlinGradleLibraryAccessors extends SubDependencyFactory {

        public KotlinGradleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for plugin (org.jetbrains.kotlin:kotlin-gradle-plugin)
         * with versionRef 'kotlin'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPlugin() {
                return create("kotlin.gradle.plugin");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final BuildsrcVersionAccessors vaccForBuildsrcVersionAccessors = new BuildsrcVersionAccessors(providers, config);
        private final ReleaseVersionAccessors vaccForReleaseVersionAccessors = new ReleaseVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: docker (9.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDocker() { return getVersion("docker"); }

            /**
             * Returns the version associated to this alias: download (5.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getDownload() { return getVersion("download"); }

            /**
             * Returns the version associated to this alias: gson (2.10.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGson() { return getVersion("gson"); }

            /**
             * Returns the version associated to this alias: jgit (6.8.0.202311291450-r)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJgit() { return getVersion("jgit"); }

            /**
             * Returns the version associated to this alias: kotlin (1.9.23)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlin() { return getVersion("kotlin"); }

        /**
         * Returns the group of versions at versions.buildsrc
         */
        public BuildsrcVersionAccessors getBuildsrc() {
            return vaccForBuildsrcVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.release
         */
        public ReleaseVersionAccessors getRelease() {
            return vaccForReleaseVersionAccessors;
        }

    }

    public static class BuildsrcVersionAccessors extends VersionFactory  {

        public BuildsrcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: buildsrc.utils (1.0.0-alpha02)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getUtils() { return getVersion("buildsrc.utils"); }

    }

    public static class ReleaseVersionAccessors extends VersionFactory  {

        public ReleaseVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: release.github (2.5.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGithub() { return getVersion("release.github"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final BuildsrcPluginAccessors paccForBuildsrcPluginAccessors = new BuildsrcPluginAccessors(providers, config);
        private final DockerPluginAccessors paccForDockerPluginAccessors = new DockerPluginAccessors(providers, config);
        private final ReleasePluginAccessors paccForReleasePluginAccessors = new ReleasePluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of plugins at plugins.buildsrc
         */
        public BuildsrcPluginAccessors getBuildsrc() {
            return paccForBuildsrcPluginAccessors;
        }

        /**
         * Returns the group of plugins at plugins.docker
         */
        public DockerPluginAccessors getDocker() {
            return paccForDockerPluginAccessors;
        }

        /**
         * Returns the group of plugins at plugins.release
         */
        public ReleasePluginAccessors getRelease() {
            return paccForReleasePluginAccessors;
        }

    }

    public static class BuildsrcPluginAccessors extends PluginFactory {

        public BuildsrcPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for buildsrc.utils to the plugin id 'io.github.sgpublic.buildsrc-utils'
             * with versionRef 'buildsrc.utils'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getUtils() { return createPlugin("buildsrc.utils"); }

    }

    public static class DockerPluginAccessors extends PluginFactory {

        public DockerPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for docker.api to the plugin id 'com.bmuschko.docker-remote-api'
             * with versionRef 'docker'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getApi() { return createPlugin("docker.api"); }

    }

    public static class ReleasePluginAccessors extends PluginFactory {

        public ReleasePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for release.github to the plugin id 'com.github.breadmoirai.github-release'
             * with versionRef 'release.github'.
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getGithub() { return createPlugin("release.github"); }

    }

}
