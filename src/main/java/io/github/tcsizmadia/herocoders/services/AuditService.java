package io.github.tcsizmadia.herocoders.services;

import io.github.tcsizmadia.herocoders.entities.Hero;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuditService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public List<Map<String, Object>> getHeroAuditHistory(Long heroId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        
        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(Hero.class, false, true)
                .add(AuditEntity.id().eq(heroId))
                .addOrder(AuditEntity.revisionNumber().asc());
        
        List<Object[]> resultList = query.getResultList();
        return resultList.stream().map(objects -> {
            Hero hero = (Hero) objects[0];
            DefaultRevisionEntity revision = (DefaultRevisionEntity) objects[1];
            RevisionType revisionType = (RevisionType) objects[2];
            
            Map<String, Object> map = new HashMap<>();
            map.put("revisionNumber", revision.getId());
            map.put("revisionType", revisionType.name());
            map.put("timestamp", revision.getRevisionDate());
            map.put("id", hero.getId());
            map.put("name", hero.getName());
            map.put("age", hero.getAge());
            map.put("health", hero.getHealth());
            return map;
        }).collect(Collectors.toList());
    }    
    
    @Transactional
    public Hero restoreHeroToRevision(Long heroId, Integer revisionNumber) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        
        // Find the entity at the specified revision
        Hero historicHero = auditReader.find(Hero.class, heroId, revisionNumber);
        
        if (historicHero == null) {
            return null;
        }
        
        // Get the current entity
        Hero currentHero = entityManager.find(Hero.class, heroId);
        if (currentHero == null) {
            return null;
        }
        
        // Update current entity with historic values
        currentHero.setName(historicHero.getName());
        currentHero.setAge(historicHero.getAge());
        currentHero.setHealth(historicHero.getHealth());
        
        // Persist changes
        entityManager.merge(currentHero);
        
        return currentHero;
    }
}
