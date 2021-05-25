package com.mtlteam.retrofitweather.rest.service.event;

import com.mtlteam.retrofitweather.rest.core.base.BaseResponseEvent;
import com.mtlteam.retrofitweather.rest.service.model.Weather;

/**
 * Event of result send via {@link org.greenrobot.eventbus.EventBus}.
 */
public class GetWeatherResponseEvent extends BaseResponseEvent<Weather> {

}
