package registration.login.utils;

import com.fasterxml.jackson.databind.JsonSerializer;

public interface PrettySerializer
{
	<T> JsonSerializer<T> getSerializer();
}
