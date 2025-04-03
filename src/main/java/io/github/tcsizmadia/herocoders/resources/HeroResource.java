package io.github.tcsizmadia.herocoders.resources;

import io.github.tcsizmadia.herocoders.entities.Hero;
import io.github.tcsizmadia.herocoders.services.HeroService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/api/heroes")
@RequestScoped
public class HeroResource {
    
    private final HeroService heroService;
    
    @Inject
    public HeroResource(HeroService heroService) {
        this.heroService = heroService;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHeroes() {
        List<Hero> heroes = heroService.getAllHeroes();
        return Response.ok(heroes).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHero(Hero hero) {
        Hero createdHero = heroService.createHero(hero);
        return Response
                .created(UriBuilder.fromResource(HeroResource.class)
                        .path(String.valueOf(createdHero.getId()))
                        .build())
                .entity(createdHero)
                .build();
    }
}
