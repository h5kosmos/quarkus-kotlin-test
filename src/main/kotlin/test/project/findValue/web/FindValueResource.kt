package test.project.findValue.web

import test.project.findValue.models.FindValue
import javax.inject.Inject
import javax.ws.rs.*

@Path("/findValue")
class FindValueResource(@Inject private val findValue: FindValue) {


    @GET
    fun getCountAllFiles(): MutableSet<String> {
        return findValue.getAllFilesNames()
    }

    @POST
    @Path("/hello/{value}")
    fun findValueInFile(@PathParam("value") value: String): MutableSet<String> {
        return findValue.findValueInFiles(value)
    }

    @POST
    @Path("/{value}")
    fun findValue(@PathParam("value") value: String): MutableSet<String>{
        return findValue.findFilesWithValue(value)
    }

    @POST
    @Path("/parseFile/{filename}")
    fun parseFile(@PathParam("filename") filename: String){
        findValue.parseFileToDb(filename)
    }

}
