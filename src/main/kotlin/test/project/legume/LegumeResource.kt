package test.project.legume

import javax.ws.rs.Consumes
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.GET

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/legumes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class LegumeResource {

    private val legumes = mutableSetOf(
        Legume("Carrot", "Root vegetable, usually orange"),
        Legume("Zucchini", "Summer squash")
    )

    @GET
    fun list(): Response? {
        return Response.ok(legumes).build()
    }
}