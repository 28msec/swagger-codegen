package io.cellstore.codegen;

import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenParameter;

import java.util.ArrayList;
import java.util.List;

public class CellStoreCodegenOperation extends CodegenOperation {
    public List<CodegenParameter> patternQueryParams = new ArrayList<CodegenParameter>();
    public List<CodegenParameter> hardcodedQueryParams = new ArrayList<CodegenParameter>();

    public Boolean autoPaginate = false;
    public String autoPaginateCountField, autoPaginateResultField;
    public List<String> autoPaginateIfNotSpecified = new ArrayList<>();
    public String autoPaginateIfNotSpecifiedCondition;

    public boolean includeOperation()
    {
      return !CellStoreCSharpClientCodegen.getBooleanExtensionValue(vendorExtensions, "x-exclude-from-bindings", false);
    }

    public void initAutoPagination(CellStoreCSharpClientCodegen codegen)
    {
      autoPaginate = CellStoreCSharpClientCodegen.getBooleanExtensionValue(vendorExtensions, "x-autoPaginate", false);
      if (autoPaginate)
      {
        autoPaginateCountField = CellStoreCSharpClientCodegen.getStringExtensionValue(vendorExtensions, "x-autoPaginateCountField");
        autoPaginateResultField = CellStoreCSharpClientCodegen.getStringExtensionValue(vendorExtensions, "x-autoPaginateResultField");
        ArrayList<String> autoPaginateIfNotSpecifiedRaw = CellStoreCSharpClientCodegen.getStringArrayExtensionValue(vendorExtensions, "x-autoPaginateIfNotSpecified");
        if (autoPaginateCountField == null || autoPaginateResultField == null || autoPaginateIfNotSpecifiedRaw == null)
          throw new RuntimeException("When x-autoPaginate is specified and true, x-autoPaginateCountField, x-autoPaginateResultField x-autoPaginateIfNotSpecified must be specified");
        if (autoPaginateIfNotSpecifiedRaw.size() == 0)
          throw new RuntimeException("x-autoPaginateIfNotSpecified cannot be empty");

        autoPaginateIfNotSpecified.clear();
        for (String item: autoPaginateIfNotSpecifiedRaw)
          autoPaginateIfNotSpecified.add(codegen.toParamName(item));
      }
    }
}
