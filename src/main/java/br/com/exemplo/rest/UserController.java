package br.com.exemplo.rest;

import br.com.exemplo.persistence.dto.UserDto;
import br.com.exemplo.persistence.model.User;
import br.com.exemplo.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/v1/users")
public class UserController {


    @Inject
    UserService userService;

    @GET
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok(users).build();
    }

    @POST
    public Response createUser(UserDto userDto) {
        userService.createUser(userDto);
        return Response.status(Response.Status.CREATED).build();
    }

}