package helpers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityDto<TConverted, UClass>
{
    public TConverted Convert(Class<TConverted> typeToConvert, UClass classToConvert)
    {
        ModelMapper modelMapper = new ModelMapper();
        TConverted classConverted = modelMapper.map(classToConvert, typeToConvert);

        return classConverted;
    }
}
