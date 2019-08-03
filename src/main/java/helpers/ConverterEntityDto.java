package helpers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityDto<T, U>
{
    public T convert(Class<T> typeToConvert, U classToConvert)
    {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(classToConvert, typeToConvert);
    }
}
