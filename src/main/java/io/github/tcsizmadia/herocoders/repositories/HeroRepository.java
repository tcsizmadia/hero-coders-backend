package io.github.tcsizmadia.herocoders.repositories;

import io.github.tcsizmadia.herocoders.entities.Hero;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class HeroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Hero> findAll() {
        return entityManager.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
    }

    public Hero findById(Long id) {
        return entityManager.find(Hero.class, id);
    }

    @Transactional
    public Hero save(Hero hero) {
        if (hero.getId() == null) {
            entityManager.persist(hero);
            return hero;
        } else {
            return entityManager.merge(hero);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        Hero hero = findById(id);
        if (hero != null) {
            entityManager.remove(hero);
        }
    }
}
