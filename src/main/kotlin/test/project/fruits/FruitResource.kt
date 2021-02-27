package test.project.fruits

import java.util.function.Predicate
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.GET


@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FruitResource {
    private val fruits = mutableSetOf(Fruit("Apple","Winter fruit"), Fruit("Pineapple", "Tropical fruit"))

    @GET
    fun getAll(): MutableSet<Fruit> {
        return fruits
    }

    @GET
    @Path("/{name}")
    fun getOne(@PathParam("name") name: String?): Fruit? {
        return fruits.find { Fruit -> Fruit.name == name }
    }

    @POST
    fun add(fruit: Fruit): MutableSet<Fruit> {
        fruits.add(fruit)
        return fruits
    }

    @DELETE
    fun delete(fruit: Fruit): MutableSet<Fruit> {
        val condition: Predicate<Fruit> = Predicate { expectedFruit -> expectedFruit.name == fruit.name }
        fruits.removeIf( condition)
        return fruits
    }
}