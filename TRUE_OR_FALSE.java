package com.company;

public class TRUE_OR_FALSE {
    //potrzebne do skracania w drzewie AVL
    private boolean truth;
    public void Change_Truth(){truth = !truth;}
    public boolean getTruth(){return truth;}
    public void toFalse(){truth = false;}
}
