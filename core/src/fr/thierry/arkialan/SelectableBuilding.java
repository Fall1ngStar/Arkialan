package fr.thierry.arkialan;

public abstract class SelectableBuilding extends Building implements Selectable{

    protected boolean selected;
    public void setSelected(boolean selected){
        this.selected = selected;
    }
}