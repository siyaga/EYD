package com.example.eyd.Data;

import com.example.eyd.Model.ModelSoal;

import java.util.ArrayList;

public class DataSoal {

        private static String[] Soal={
                "1. Manakah Tulisan EYD Yang Benar ?",
                "2. Manakah Tulisan EYD Yang Benar ?",
                "3. Manakah Tulisan EYD Yang Benar ?",
                "4. Manakah Tulisan EYD Yang Benar ?",
                "5. Manakah Tulisan EYD Yang Benar ?",
                "6. Manakah Tulisan EYD Yang Benar ?",
                "7. Manakah Tulisan EYD Yang Benar ?",
                "8. Manakah Tulisan EYD Yang Benar ?",
                "9. Manakah Tulisan EYD Yang Benar ?",
                "10. Manakah Tulisan EYD Yang Benar ?"
        };
        private static String[] Jawaban1={
                "Silahkan",
                "Antri",
                "Sekedar",
                "Dimana",
                "Aktifitas",
                "Praktek",
                "Analisa",
                "Nasehat",
                "Resiko",
                "Perduli"

        };
        private static String[] Jawaban2={
                "Silakan",
                "Antre",
                "Sekadar",
                "Di mana",
                "Aktivitas",
                "Praktik",
                "Analisis",
                "Nasihat",
                "Risiko",
                "Peduli"

        };
        public static ArrayList<ModelSoal> getListData() {
            ArrayList<ModelSoal> list = new ArrayList<>();
            for (int position = 0; position < Soal.length; position++) {
                ModelSoal modelSoal = new ModelSoal();
                modelSoal.setSoal(Soal[position]);
                modelSoal.setJawaban1(Jawaban1[position]);
                modelSoal.setJawaban2(Jawaban2[position]);
                list.add(modelSoal);
            }
            return list;
        }


}
