package rodrigo.salles.springrecipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import rodrigo.salles.springrecipe.model.Recipe;
import rodrigo.salles.springrecipe.model.User;
import rodrigo.salles.springrecipe.service.RecipeService;
import rodrigo.salles.springrecipe.service.UserService;

@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader ("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);
        Recipe createdRecipe=recipeService.createRecipe(recipe, user);
        return createdRecipe;
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{

        Recipe updatedRecipe=recipeService.updateRecipe(recipe, id);
        return updatedRecipe;
    }

    @GetMapping
    public List<Recipe> getAllRecipe() throws Exception {
        List<Recipe> recipes = recipeService.findAllRecipe();
        return recipes;
    }

    @DeleteMapping("/{recipeId}")
    public List<Recipe> deleteRecipe (@PathVariable Long recipeId) throws Exception {
        List<Recipe> recipes = recipeService.findAllRecipe();
        return recipes;
    }

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Recipe updatedRecipe=recipeService.likeRecipe(id,user);
        return updatedRecipe;
    }
}
