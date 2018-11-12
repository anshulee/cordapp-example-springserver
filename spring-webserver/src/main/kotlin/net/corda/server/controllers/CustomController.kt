package net.corda.server.controllers

import com.example.state.IOUState
import net.corda.core.messaging.vaultQueryBy
import net.corda.server.NodeRPCConnection
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * Define CorDapp-specific endpoints in a controller such as this.
 */
@RestController
@RequestMapping("/custom") // The paths for GET and POST requests are relative to this base path.
class CustomController(
        private val rpc: NodeRPCConnection) {

    companion object {
        private val logger = LoggerFactory.getLogger(RestController::class.java)
    }

    private val proxy = rpc.proxy

    @GetMapping(value = "/customendpoint", produces = arrayOf("text/plain"))
    private fun status() = "Modify this."

    /**
     * Displays all IOU states that exist in the node's vault.
     */
    @GetMapping(value="/ious",  produces= arrayOf("application/json"))

    fun getIOUs() = rpc.proxy.vaultQueryBy<IOUState>().states

}