package br.com.exemplo.rest;

import br.com.exemplo.persistence.dto.UserDto;
import br.com.exemplo.persistence.model.User;
import br.com.exemplo.services.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/v1/users")
public class UserController {

    @Inject
    UserService userService;


    @GET
    public Response getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return Response.status(Response.Status.OK).entity(users).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao obter usuários.").build();
        }
    }

    @Path("/{id}")
    @GET
    public Response getUser(@PathParam("id") Long id) {
        try {
            Optional<User> user = userService.getUser(id);
            return Response.status(Response.Status.OK).entity(user).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao obter usuário.").build();
        }
    }

    @POST
    public Response createUser(@Valid UserDto userDto) {
        this.userService.createUser(userDto);
        return Response.status(Response.Status.CREATED).entity("Usuário cadastrado com sucesso!").build();
    }


    @PUT
    public Response updateUser(@Valid UserDto userDto) {
        try {
            userService.updateUser(userDto);
            return Response.status(Response.Status.OK).entity("Usuário atualizado com sucesso.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar usuário.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            userService.deleteUser(id);
            return Response.status(Response.Status.OK).entity("Usuário excluído com sucesso.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir usuário.").build();
        }
    }

}