package services;

import com.google.gson.Gson;
import dto.DataResponse;
import exceptions.FixerException;
import helpers.Symbols;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateService {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final String ACCESS_KEY = "93572e610925cf77857fa82576bfd426";
    private final double SPREAD = 0.5;

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    public String calculate(String date, Double amount) throws ParseException, IOException, FixerException {
        Date nowDate = new Date();
        String now = formatter.format(nowDate);
        if (nowDate.getTime() < formatter.parse(date).getTime()) {
            throw new IOException("Введенная дата еще не наступила.");
        }

        DataResponse currentData = getData(now);
        if (!currentData.getSuccess()) {
            throw new FixerException(currentData.getError().getInfo());
        }

        DataResponse pastData = getData(date);
        if (!currentData.getSuccess()) {
            throw new FixerException(pastData.getError().getInfo());
        }

        double currentRubRate = getRubRate(Symbols.USD, currentData);
        double pastRubRate = getRubRate(Symbols.USD, pastData);

        double result = amount * (currentRubRate - pastRubRate) * SPREAD;
        return String.format("%.2f", result);
    }

    private double getRubRate(Symbols currency, DataResponse data) throws FixerException {
        if (data.getRates() == null) throw new FixerException("курс валют не получен.");

        double rubRate = data.getRates().get(Symbols.RUB.name());

        if (currency.name().equals(data.getBase())) {
            return rubRate;
        } else {
            double currencyRate = data.getRates().get(currency.name());
            if (currencyRate == 0) throw new FixerException("получен некорректный курс для " + currency.name() + ".");
            return rubRate / currencyRate;
        }
    }

    private DataResponse getData(String date) throws IOException {
        String url = "http://data.fixer.io/api/" + date + "?access_key=" + ACCESS_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        if (response.body() == null) {
            throw new IOException("Ошибка при получении данных от сервера.");
        }

        String serverAnswer = response.body().string();

        return gson.fromJson(serverAnswer, DataResponse.class);
    }
}
