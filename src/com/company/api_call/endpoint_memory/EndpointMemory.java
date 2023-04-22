package com.company.api_call.endpoint_memory;

import com.company.api_call.endpoint_data.EndpointData;
import com.company.api_call.endpoint_data.EndpointDataInterface;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;
import com.company.tool.util.Pair;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

final public class EndpointMemory implements EndpointMemoryInterface {

    private final Map<Pair<CryptoCurrencies, FiatCurrencies>, EndpointDataInterface> endpointDataMap;

    private final CryptoCurrencies[] acceptedCryptos;

    // TODO: Figure out if I actually need this
    private final FiatCurrencies[] acceptedFiats;


    public EndpointMemory(final CryptoCurrencies[] acceptedCryptos, final FiatCurrencies[] acceptedFiats) {
        this.endpointDataMap = new HashMap<>();
        this.acceptedCryptos = acceptedCryptos;
        this.acceptedFiats = acceptedFiats;

        for (final CryptoCurrencies crypto : this.acceptedCryptos) {
            for (final FiatCurrencies fiat : this.acceptedFiats) {
                this.endpointDataMap.put(new Pair<>(crypto, fiat), new EndpointData());
            }
        }
    }


    private EndpointDataInterface getDataFromMap(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        try {
            return this.endpointDataMap.get(new Pair<>(crypto, fiat));
        } catch (final NullPointerException e) {
            if (!Arrays.asList(this.acceptedCryptos).contains(crypto)) throw  new CryptoCurrencyNotSupported(crypto);
            if (!Arrays.asList(this.acceptedFiats).contains(fiat)) throw new FiatCurrencyNotSupported(fiat);
        }
        return null;
    }

    @Override
    public double getPrice(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        return this.getDataFromMap(crypto, fiat).getPrice();
    }

    @Override
    public void setPrice(final CryptoCurrencies crypto, final FiatCurrencies fiat, final double price)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        this.getDataFromMap(crypto, fiat).setPrice(price);
    }

    @Override
    public boolean isUpdating(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        return this.getDataFromMap(crypto, fiat).isUpdating();
    }

    @Override
    public void setUpdating(final CryptoCurrencies crypto, final FiatCurrencies fiat, boolean isUpdating)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        this.getDataFromMap(crypto, fiat).setUpdating(isUpdating);
    }

    @Override
    public LocalDateTime getLastSuccessfulUpdated(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        return this.getDataFromMap(crypto, fiat).getLastSuccessfulUpdate();
    }

    @Override
    public void setLastSuccessfulUpdated(final CryptoCurrencies crypto, final FiatCurrencies fiat,
                                         final LocalDateTime lastUpdated)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        this.getDataFromMap(crypto, fiat).setLastSuccessfulUpdate(lastUpdated);
    }

    @Override
    public boolean wasLastUpdateSuccessful(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        return this.getDataFromMap(crypto, fiat).wasLastUpdateSuccessful();
    }

    @Override
    public void setWasLasUpdateSuccessful(final CryptoCurrencies crypto, final FiatCurrencies fiat,
                                          final boolean successful)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        this.getDataFromMap(crypto, fiat).setWasLastUpdateSuccessful(successful);
    }
}
