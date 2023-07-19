import { useMemo, useState, useEffect } from 'react';
import { useTable, useSortBy, usePagination } from 'react-table';
import '../styles/FuelQuoteHistoryPageStyles.css';

const loggedUserId = 1; // Simulated logged-in user ID
let page = 0;

const FuelQuoteHistoryPage = () => {
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [fuelQuoteHistory, setFuelQuoteHistory] = useState([]);

  // Fetch fuel quote history
  useEffect(() => {
    fetch(`http://localhost:8080/api/fuelquote/history?userId=${loggedUserId}`)
      .then(response => response.json())
      .then(data => {
        setFuelQuoteHistory(data);
        console.log('Fuel quote history:', data);
      });
  }, []);

  const columns = useMemo(
    () => [
      {
        Header: 'Gallons Requested',
        accessor: 'gallonsRequested',
      },
      {
        Header: 'Delivery Address',
        accessor: 'deliveryAddress',
      },
      {
        Header: 'Delivery Date',
        accessor: 'deliveryDate',
      },
      {
        Header: 'Suggested Price',
        accessor: 'suggestedPrice',
      },
      {
        Header: 'Total Amount Due',
        accessor: 'totalAmountDue',
      },
    ],
    []
  );

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    prepareRow,
    page: tablePage,
    gotoPage,
    canPreviousPage,
    canNextPage,
    pageCount,
    setPageSize,
    state
  } = useTable(
    {
      columns,
      data: fuelQuoteHistory,
      initialState: { pageIndex: 0 },
    },
    useSortBy,
    usePagination
  );

  const handleChangePage = (event, newPage) => {
    gotoPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    const newPageSize = parseInt(event.target.value, 10);
    setRowsPerPage(newPageSize);
    setPageSize(newPageSize);
    gotoPage(0); // Reset React Table's internal page state
  };

  return (
    <div>
      <h1 className="FormTitle mb-6">Fuel Quote History</h1>
      <div className="TableHolder">
        <table {...getTableProps()} className="Table">
          <thead>
            {headerGroups.map((headerGroup, index) => (
              <tr {...headerGroup.getHeaderGroupProps()} key={index} className="TableRow">
                {headerGroup.headers.map(column => (
                  <th
                    {...column.getHeaderProps(column.getSortByToggleProps())}
                    key={column.id}
                    className="TableCell"
                  >
                    <div>
                      {column.render('Header')}
                      {/* Add a sort direction indicator */}
                      <span>{column.isSorted ? (column.isSortedDesc ? ' 🔽' : ' 🔼') : ''}</span>
                    </div>
                  </th>
                ))}
              </tr>
            ))}
          </thead>
          <tbody {...getTableBodyProps()}>
            {tablePage.map((row, rowIndex) => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()} key={rowIndex} className="TableRow">
                  {row.cells.map((cell, cellIndex) => (
                    <td {...cell.getCellProps()} key={cellIndex} className="TableCell">
                      {cell.render('Cell')}
                    </td>
                  ))}
                </tr>
              );
            })}
          </tbody>
        </table>
        <div className="TablePagination">
          <div>
            <span>Rows per page:</span>
            <select value={rowsPerPage} onChange={handleChangeRowsPerPage} className="TablePaginationSelect">
              <option value={10}>10</option>
              <option value={25}>25</option>
              <option value={100}>100</option>
            </select>
          </div>
          <div className="PaginationButtonsContainer">
            <button
              onClick={() => { handleChangePage(null, 0); page = 0; }}
              disabled={page === 0}
              className="TablePaginationButton">
              {'<<'}
            </button>
            <button
              onClick={() => handleChangePage(null, page -= 1)}
              disabled={!gotoPage || !canPreviousPage}
              className="TablePaginationButton"
            >
              {'<'}
            </button>
            <button
              onClick={() => handleChangePage(null, page += 1)}
              disabled={!gotoPage || !canNextPage}
              className="TablePaginationButton"
            >
              {'>'}
            </button>
            <button
              onClick={() => { handleChangePage(null, pageCount - 1); page = pageCount - 1 }}
              disabled={!gotoPage || !canNextPage}
              className="TablePaginationButton"
            >
              {'>>'}
            </button>
          </div>
          <div>
            <span>
              Page{' '}
              <input
                type="number"
                value={state.pageIndex + 1}
                onChange={event => {
                  const newPage = event.target.value > 0 ? Number(event.target.value) - 1 : 0;
                  page = newPage;
                  handleChangePage(null, newPage);
                }}
                className="TablePaginationInput PageInput"
              />
              of {pageCount}
            </span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FuelQuoteHistoryPage;
