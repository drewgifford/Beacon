package com.drewgifford.beacon.entry;

import com.drewgifford.beacon.ChatUtil;

import java.util.List;

public class BeaconPermission {

    public static boolean checkPermission(List<String> permissions, String permissionToCheck){
        String[] checkAgainst = permissionToCheck.toLowerCase().split("\\.");

        //TODO: Permission functions
        // Probably at the end, i.e.
        // players.inventory.amount | x < 10

        for (String s : permissions){
            s = s.toLowerCase();


            String[] rival = s.split("\\.");

            boolean status = false;

            int length = checkAgainst.length;

            for (int i = 0; i < length; i++){

                String againstPart = checkAgainst[i];
                String rivalPart = rival[i];

                if (againstPart.equalsIgnoreCase(rivalPart)){
                    status = true;
                    continue;
                }

                if(rivalPart.equalsIgnoreCase("*")){
                    status = true;
                    break;
                }
                status = false;
                break;



            }

            if(status){
                return true;
            }




        }
        return false;


    }
}
