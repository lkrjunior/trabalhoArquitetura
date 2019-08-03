package helpers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityDto<TYPETOCONVERT, CLASS>
{
    public TYPETOCONVERT convert(Class<TYPETOCONVERT> typeToConvert, CLASS classToConvert)
    {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(classToConvert, typeToConvert);
    }
}
