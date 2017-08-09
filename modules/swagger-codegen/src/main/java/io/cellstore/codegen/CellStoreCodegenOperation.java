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

    public boolean includeOperation()
    {
      return !CellStoreCSharpClientCodegen.getBooleanExtensionValue(vendorExtensions, "x-exclude-from-bindings", false);
    }

    public void initAutoPagination()
    {
      autoPaginate = CellStoreCSharpClientCodegen.getBooleanExtensionValue(vendorExtensions, "x-autoPaginate", false);
      if (autoPaginate)
      {
        autoPaginateCountField = CellStoreCSharpClientCodegen.getStringExtensionValue(vendorExtensions, "x-autoPaginateCountField");
        autoPaginateResultField = CellStoreCSharpClientCodegen.getStringExtensionValue(vendorExtensions, "x-autoPaginateResultField");
        if (autoPaginateCountField == null || autoPaginateResultField == null)
          throw new RuntimeException("When x-autoPaginate is specified and true, x-autoPaginateCountField and x-autoPaginateResultField must be specified");
      }
    }
}
