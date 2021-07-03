package com.drewgifford.beacon.entry;

import com.drewgifford.beacon.ChatUtil;

import java.util.List;

public class BeaconPermission {

    public static boolean checkPermission(List<String> permissions, String permissionToCheck){
        String[] checkAgainst = permissionToCheck.toLowerCase().split("\\.");
        ChatUtil.sendMessage(null, "CHECKING PERMISSION", permissionToCheck);

        //TODO: Permission functions
        // Probably at the end, i.e.
        // players.inventory.amount | x < 10

        for (String s : permissions){
            s = s.toLowerCase();

            ChatUtil.sendMessage(null, "CHECKING PERMISSION", s, "AGAINST", permissionToCheck);

            String[] rival = s.split("\\.");

            //test case
            // players.inventory.*
            // players.inventory.amount

            boolean status = false;

            int length = checkAgainst.length;

            for (int i = 0; i < length; i++){

                String againstPart = checkAgainst[i];
                String rivalPart = rival[i];


                ChatUtil.sendMessage(null, "CHECKING", againstPart, rivalPart);

                if (againstPart.equalsIgnoreCase(rivalPart)){
                    status = true;
                    ChatUtil.sendMessage(null, "CHECK RESULT: ", Boolean.toString(status));
                    continue;
                }

                if(rivalPart.equalsIgnoreCase("*")){
                    status = true;
                    ChatUtil.sendMessage(null, "CHECK RESULT: ", Boolean.toString(status));
                    break;
                }
                status = false;
                ChatUtil.sendMessage(null, "CHECK RESULT: ", Boolean.toString(status));
                break;



            }

            if(status){
                return true;
            }




        }
        return false;


    }
}
