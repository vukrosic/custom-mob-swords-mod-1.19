package net.vukrosic.custommobswordsmod.entity.custom.summoner;

import java.util.ArrayList;

public class SummonerEntityRemoval {
    static ArrayList<SummonerEntityGL> summoners = new ArrayList<SummonerEntityGL>();
    // add
    public static void addSummoner(SummonerEntityGL summoner){
        summoners.add(summoner);
    }
    public static void clear(){
        summoners.clear();
    }
}
