package rodrigo.salles.springrecipe.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import rodrigo.salles.springrecipe.model.Recipe;
import rodrigo.salles.springrecipe.model.User;
import rodrigo.salles.springrecipe.repo.RecipeRepo;

public class RecipeServiceImpl implements RecipeService {

    private RecipeRepo recipeRepo;

    public RecipeServiceImpl(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createdRecipe = new Recipe();
        createdRecipe.setTitle(recipe.getTitle());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setDescription(recipe.getDescription());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(LocalDateTime.now());

        return recipeRepo.save(createdRecipe);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeByid(id);
        recipeRepo.deleteById(id);

    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepo.findAll();
    }

    @Override
    public Recipe findRecipeByid(Long id) throws Exception {
        @SuppressWarnings("null")
        Optional<Recipe> opt = recipeRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("recipe not found with id " + id);
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe = findRecipeByid(recipeId);
        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepo.save(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long Id) throws Exception {
        Recipe oldRecipe = findRecipeByid(Id);
        
        if(recipe..getTitle()!=null){
            oldRecipe.setTitle(recipe.getTitle());
        }
        if(recipe.getImage() !=null ){
            oldRecipe.setImage(recipe.getImage());
        }
        if(recipe.getDescription()!= null) {
            oldRecipe.setDescription(recipe.getDescription());
        }

        return recipeRepo.save(oldRecipe);
    }

}
