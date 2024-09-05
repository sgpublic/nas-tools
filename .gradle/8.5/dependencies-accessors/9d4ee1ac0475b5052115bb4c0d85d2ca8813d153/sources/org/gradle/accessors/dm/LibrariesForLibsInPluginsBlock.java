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
public class LibrariesForLibsInPluginsBlock extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final DownloadLibraryAccessors laccForDownloadLibraryAccessors = new DownloadLibraryAccessors(owner);
    private final JgitLibraryAccessors laccForJgitLibraryAccessors = new JgitLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibsInPluginsBlock(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for gson (com.google.code.gson:gson)
     * with versionRef 'gson'.
         * This dependency was declared in catalog libs.versions.toml
     * @deprecated Will be removed in Gradle 9.0.
         */
    @Deprecated
        public Provider<MinimalExternalModuleDependency> getGson() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return create("gson");
    }

    /**
     * Returns the group of libraries at download
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public DownloadLibraryAccessors getDownload() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForDownloadLibraryAccessors;
    }

    /**
     * Returns the group of libraries at jgit
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public JgitLibraryAccessors getJgit() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForJgitLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlin
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public KotlinLibraryAccessors getKotlin() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
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
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public BundleAccessors getBundles() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class DownloadLibraryAccessors extends SubDependencyFactory {
        private final DownloadGradleLibraryAccessors laccForDownloadGradleLibraryAccessors = new DownloadGradleLibraryAccessors(owner);

        public DownloadLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at download.gradle
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public DownloadGradleLibraryAccessors getGradle() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForDownloadGradleLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class DownloadGradleLibraryAccessors extends SubDependencyFactory {

        public DownloadGradleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for plugin (de.undercouch.download:de.undercouch.download.gradle.plugin)
         * with versionRef 'download'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getPlugin() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("download.gradle.plugin");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JgitLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public JgitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jgit (org.eclipse.jgit:org.eclipse.jgit)
         * with versionRef 'jgit'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> asProvider() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("jgit");
        }

            /**
             * Creates a dependency provider for apache (org.eclipse.jgit:org.eclipse.jgit.ssh.apache)
         * with versionRef 'jgit'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getApache() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("jgit.apache");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class KotlinLibraryAccessors extends SubDependencyFactory {
        private final KotlinGradleLibraryAccessors laccForKotlinGradleLibraryAccessors = new KotlinGradleLibraryAccessors(owner);

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at kotlin.gradle
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public KotlinGradleLibraryAccessors getGradle() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForKotlinGradleLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class KotlinGradleLibraryAccessors extends SubDependencyFactory {

        public KotlinGradleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for plugin (org.jetbrains.kotlin:kotlin-gradle-plugin)
         * with versionRef 'kotlin'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getPlugin() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
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

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
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
