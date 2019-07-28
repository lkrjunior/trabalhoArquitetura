package helpers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityDto<TConverted, UClass>
{
    public TConverted convert(Class<TConverted> typeToConvert, UClass classToConvert)
    {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(classToConvert, typeToConvert);
    }
}
