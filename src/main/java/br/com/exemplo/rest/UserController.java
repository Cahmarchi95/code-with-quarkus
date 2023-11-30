package br.com.exemplo.rest;

import br.com.exemplo.persistence.dao.UserRepository;
import br.com.exemplo.persistence.model.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/v1/users")
public class UserController {

    @Inject
    UserRepository userRepository;

    @GET
    public Response listUsers() {
        List<User> users = userRepository.listAll();
        return Response.ok(users).build();
    }

    @POST
    @Transactional
    public Response addUser(User user) {
        userRepository.persist(user);
        return Response.status(Response.Status.CREATED).build();
    }
}