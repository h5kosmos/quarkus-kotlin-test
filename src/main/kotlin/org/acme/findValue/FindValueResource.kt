package org.acme.findValue

import java.io.File
import javax.inject.Inject
import javax.ws.rs.*

@Path("/findValue")
class FindValueResource(@Inject private val findValue: FindValue) {


    @GET
    fun getCountAllFiles(): MutableSet<File> {
        return findValue.getAllFiles()
    }


    @POST
    @Path("/{value}")
    fun findValue(@PathParam("value") value: String): MutableSet<String>{
        return findValue.findFilesWithValue(value)
    }
}
