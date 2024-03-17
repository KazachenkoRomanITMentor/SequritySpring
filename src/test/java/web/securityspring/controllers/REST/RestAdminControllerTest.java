package web.securityspring.controllers.REST;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import web.securityspring.TestConfig;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest(classes = TestConfig.class)
@Transactional
@AutoConfigureMockMvc
@WithMockUser(username = "Admin", roles = "ADMIN")
@Sql({"/sql/users.sql"})
class RestAdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllUsers_returnsUsersList() throws Exception {
        var request = MockMvcRequestBuilders.get("/api/v1/admin/");
        this.mockMvc.perform(request)
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                [{"username":"username1","name":"name1","surname":"surname1","age":10,"roles":[]},
                {"username":"username2","name":"name2","surname":"surname2","age":20,"roles":[]},
                {"username":"username3","name":"name3","surname":"surname3","age":30,"roles":[]}]
                """)
                );
    }

    @Test
    public void getUserById_returnsUsersById() throws Exception {
        var request = MockMvcRequestBuilders.get("/api/v1/admin/user/{id}", 1L);
        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("username1")))
                .andExpect(jsonPath("$.name", is("name1")))
                .andExpect(jsonPath("$.surname", is("surname1")))
                .andExpect(jsonPath("$.age", is(10)));
    }

    @Test
    public void create_ReturnCreatedUser() throws Exception {
        var request = MockMvcRequestBuilders.post("/api/v1/admin/adduser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {"username":"username4","name":"name4",
                         "surname":"surname4","age":50,"password":"password4"}
                        """);

        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("username4")))
                .andExpect(jsonPath("$.name", is("name4")))
                .andExpect(jsonPath("$.surname", is("surname4")))
                .andExpect(jsonPath("$.age", is(50)))
                .andExpect(jsonPath("$.roles").isArray());
    }

    @Test
    public void update_ReturnUpdatableUser() throws Exception{
        var request = MockMvcRequestBuilders.patch("/api/v1/admin/edit/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {"id":1, "username":"username1","name":"name1",
                         "surname":"surname1","age":40,"password":"password1"}
                        """);

        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.age", is(40)));
    }

    @Test
    public void delete_DeletableUser() throws Exception {
        var request = MockMvcRequestBuilders.delete("/api/v1/admin/delete/{id}", 1L);

          this.mockMvc.perform(request)
                .andExpect(status().isNoContent());

    }
}