package com.wipro.persistencia;

import static com.wipro.constants.Constants.DIR_ARQ;
import static com.wipro.util.FormatToolKit.isEmpty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.wipro.model.DVD;

public class DVDDAO {

	public static ArrayList<DVD> getListaDVD() throws IOException {
		ArrayList<DVD> listaDVD = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(DIR_ARQ));
		String linha = null;
		try {
			while ((linha = br.readLine()) != null) {
				if (!isEmpty(linha)) {
					String[] valores = linha.split(";");
					DVD dvd = newDVD(valores);
					listaDVD.add(dvd);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally {
			br.close();
		}
		return listaDVD;
	}

	private static DVD newDVD(String[] valores) {
		DVD dvd = new DVD();
		dvd.setNome(valores[0]);
		dvd.setDiretor(valores[1]);
		dvd.setAnoDePublicacao(Integer.parseInt(valores[2]));
		dvd.setSinopse(valores[3]);
		return dvd;
	}

	public static void saveAllDVD(DVD dvd) throws IOException{
		ArrayList<DVD> listaDVD = getListaDVD();
		listaDVD.add(dvd);
		String fileName = DIR_ARQ;
		File fileTemp = new File(fileName + ".tmp");
		if (fileTemp.exists()) {
			fileTemp.delete();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp));
		for (DVD dvd2 : listaDVD) {
			String registro = getRegistro(dvd2);
			bw.write(registro);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
		fileTemp.renameTo(file);
	}

	private static String getRegistro(DVD dvd2) {
		String registro = null;
		StringBuilder sb = new StringBuilder();
		sb.append(dvd2.getNome()+";");
		sb.append(dvd2.getDiretor()+";");
		sb.append(dvd2.getAnoDePublicacao()+";");
		sb.append(dvd2.getSinopse());
		registro = sb.toString();
		return registro;
	}
	
}
