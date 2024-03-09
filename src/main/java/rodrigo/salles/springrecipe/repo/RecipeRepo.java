package rodrigo.salles.springrecipe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import rodrigo.salles.springrecipe.model.Recipe;

public interface RecipeRepo  extends JpaRepository <Recipe, Long>{
    
}
