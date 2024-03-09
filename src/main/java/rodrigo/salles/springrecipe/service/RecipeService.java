package rodrigo.salles.springrecipe.service;

import java.util.List;

import rodrigo.salles.springrecipe.model.Recipe;
import rodrigo.salles.springrecipe.model.User;

public interface RecipeService {

    public Recipe createRecipe (Recipe recipe, User user);
    public Recipe findRecipeByid(Long id) throws Exception;
    public void deleteRecipe (Long id) throws Exception;
    public Recipe updateRecipe(Recipe recipe, Long Id) throws Exception;
    public List<Recipe>findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user) throws Exception;
    
}
