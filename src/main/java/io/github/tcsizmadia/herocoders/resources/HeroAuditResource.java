package io.github.tcsizmadia.herocoders.resources;

import io.github.tcsizmadia.herocoders.services.AuditService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/heroes")
public class HeroAuditResource {
    
    @Inject
    private AuditService auditService;
    
    @GET
    @Path("/{id}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeroHistory(@PathParam("id") Long id) {
        return Response.ok(auditService.getHeroAuditHistory(id)).build();
    }
    
    @POST
    @Path("/{id}/restore/{revisionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response restoreHeroToRevision(@PathParam("id") Long id, @PathParam("revisionId") Integer revisionId) {
        try {
            return Response.ok(auditService.restoreHeroToRevision(id, revisionId)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to restore hero: " + e.getMessage()).build();
        }
    }
}
