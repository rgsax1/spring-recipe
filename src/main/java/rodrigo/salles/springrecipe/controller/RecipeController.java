package rodrigo.salles.springrecipe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rodrigo.salles.springrecipe.model.Recipe;
import rodrigo.salles.springrecipe.model.User;
import rodrigo.salles.springrecipe.service.RecipeService;
import rodrigo.salles.springrecipe.service.UserService;

@RequestMapping("/api/recipes")
public class RecipeController {
    private RecipeService recipeService;
    private UserService userService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
  
    public RecipeController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception{

        User user = userService.findUserById(userId);
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

    @PutMapping("/{id}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long userId, @PathVariable Long id) throws Exception {
        User user = userService.findUserById(userId);
        Recipe updatedRecipe=recipeService.likeRecipe(id,user);
        return updatedRecipe;
    }
}
