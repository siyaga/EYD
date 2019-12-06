package com.example.eyd.Data;

import com.example.eyd.Model.ModelMateri;

import java.util.ArrayList;

public class DataMateri {
        private static String[] Materi={
                "Materi ejaan Van Ophuysen",
                "Materi peleburan kata dasar",
                "Materi 3",
                "Materi 4",
                "Materi 5",
                "Materi 6",
                "Materi 7",
                "Materi 8",
                "Materi 9"

    };
    public static ArrayList<ModelMateri> getListData() {
        ArrayList<ModelMateri> list = new ArrayList<>();
        for (int position = 0; position < Materi.length; position++) {
            ModelMateri modelMateri = new ModelMateri();
            modelMateri.setMateri(Materi[position]);
            list.add(modelMateri);
        }
        return list;
    }
}
