package model;

import java.util.List;

public abstract class IItemsData {
    public abstract List<String> getCategories();

    public abstract List<FoodItem> getItemsByCat(String cat);
}
