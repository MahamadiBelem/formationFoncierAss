package bf.agriculture.dgfomr.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, bf.agriculture.dgfomr.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, bf.agriculture.dgfomr.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, bf.agriculture.dgfomr.domain.User.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Authority.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.User.class.getName() + ".authorities");
            createCache(cm, bf.agriculture.dgfomr.domain.Region.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Provinces.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Communes.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Villages.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.ConditionAccess.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.ConditionAccess.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.ApprochePedagogique.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.ApprochePedagogique.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.Specialites.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Specialites.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.DomaineFormation.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.DomaineFormation.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.Contributions.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Contributions.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.Gestionnaire.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Promoteurs.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.PublicCible.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.PublicCible.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.NiveauRecrutement.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.NiveauRecrutement.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".infrastructures");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".amenagements");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".approchepedagogiques");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".publiccibles");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".specialites");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".domaineformations");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".contributions");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".niveaurecrutements");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".formateurs");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".conditionaccesses");
            createCache(cm, bf.agriculture.dgfomr.domain.CentreFormation.class.getName() + ".formations");
            createCache(cm, bf.agriculture.dgfomr.domain.TypeInfratructure.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.TypeAmenagement.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Infrastructure.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Amenagement.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Formateurs.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Formateurs.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.Apprenantes.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Apprenantes.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.Formations.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Formations.class.getName() + ".formations");
            createCache(cm, bf.agriculture.dgfomr.domain.Formations.class.getName() + ".centreformations");
            createCache(cm, bf.agriculture.dgfomr.domain.NiveauInstruction.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.TypeFormation.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.SortiePromotion.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Installation.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Installation.class.getName() + ".activiteinstallations");
            createCache(cm, bf.agriculture.dgfomr.domain.Installation.class.getName() + ".kits");
            createCache(cm, bf.agriculture.dgfomr.domain.ActiviteInstallation.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.ActiviteInstallation.class.getName() + ".installations");
            createCache(cm, bf.agriculture.dgfomr.domain.Kits.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Kits.class.getName() + ".installations");
            createCache(cm, bf.agriculture.dgfomr.domain.CompositionKits.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.CompositionKits.class.getName() + ".kits");
            createCache(cm, bf.agriculture.dgfomr.domain.Region.class.getName() + ".provinces");
            createCache(cm, bf.agriculture.dgfomr.domain.Provinces.class.getName() + ".communes");
            createCache(cm, bf.agriculture.dgfomr.domain.Communes.class.getName() + ".villages");
            createCache(cm, bf.agriculture.dgfomr.domain.Typecandidat.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Apprenantes.class.getName() + ".niveauInstructions");
            createCache(cm, bf.agriculture.dgfomr.domain.Regime.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Regime.class.getName() + ".centreFormations");
            createCache(cm, bf.agriculture.dgfomr.domain.Inscription.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.NiveauInstruction.class.getName() + ".apprenantes");
            createCache(cm, bf.agriculture.dgfomr.domain.Kits.class.getName() + ".installationKits");
            createCache(cm, bf.agriculture.dgfomr.domain.FormateurCentre.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.FormationCentreFormation.class.getName());
            createCache(cm, bf.agriculture.dgfomr.domain.Regime.class.getName() + ".formateurCentres");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
