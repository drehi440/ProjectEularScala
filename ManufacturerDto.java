package com.neilsen.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.neilson.read.GenricDao;

public class ManufacturerDto {
	private static final Map<Integer, ManufacturerDto> manifacturerMap = new HashMap<Integer, ManufacturerDto>();
	int manifacutureId;
	String name;

	public ManufacturerDto(final int manifacutureId, final String name) {
		super();
		this.manifacutureId = manifacutureId;
		this.name = name;
	}

	public static void loadManufacturerData(final GenricDao genricDao) {
		final String qury = "Select * from imdb.Manufacturer";
		final String fileName = "Manufacturer";
		final ResultSet rs = genricDao.getResult(qury);
		final Stream<String> st = genricDao.getStream(fileName);

		try {
			while (rs.next()) {
				final ManufacturerDto manifacturerDto = new ManufacturerDto(
						rs.getInt(1), rs.getString(2));
				manifacturerMap.put(rs.getInt(1), manifacturerDto);
			}

			st.skip(1)
					.map((line) -> {
						String[] obj = line.split(",");
						System.out.println(line);
						final ManufacturerDto manifacturerDto = new ManufacturerDto(Integer.parseInt(obj[0]), obj[1]);
						manifacturerMap.put(Integer.parseInt(obj[0]),manifacturerDto);
						return manifacturerDto;

					}).forEach(System.out::println);

			rs.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	public static Map<Integer, ManufacturerDto> getManifacturermap() {
		return manifacturerMap;
	}

	public int getManifacutureId() {
		return manifacutureId;
	}

	public void setManifacutureId(final int manifacutureId) {

		this.manifacutureId = manifacutureId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ManufacturerDto [manifacutureId=" + manifacutureId + ", name="
				+ name + "]";
	}

}
