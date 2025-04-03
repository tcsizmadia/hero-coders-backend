package io.github.tcsizmadia.herocoders.services;

import io.github.tcsizmadia.herocoders.entities.Hero;
import io.github.tcsizmadia.herocoders.repositories.HeroRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class HeroService {
    
    private final HeroRepository heroRepository;
    
    @Inject
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }
    
    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }
    
    public Hero createHero(Hero hero) {
        return heroRepository.save(hero);
    }
}
