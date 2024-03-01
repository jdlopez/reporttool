package es.jdlopez.reporttool.domain;

import lombok.Data;

import java.util.List;

/**
 * Report result aata compatible with: https://datatables.net/
 */
@Data
public class ResultData {
    private List<DatatablesColumnDef> columns;
    private List<RowData> data;
}
