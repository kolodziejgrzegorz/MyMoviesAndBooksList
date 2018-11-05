package pl.list.BooksAndMovies.utils.conventers;

import pl.list.BooksAndMovies.database.models.Category;
import pl.list.BooksAndMovies.modelFx.CategoryFx;

public class CategoryConverter {

    public static CategoryFx convertToCategoryFx(Category c){
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(c.getId());
        categoryFx.setName(c.getName());
        return categoryFx;
    }
}
