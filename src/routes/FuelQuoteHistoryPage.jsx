import { useMemo, useState } from 'react';
import { useTable, useSortBy, usePagination } from 'react-table';
import '../styles/FuelQuoteHistoryPageStyles.css';

// sample data to represent what you would fetch from the backend
const sampleData = [
  {
    id: 1,
    user_id: 'abc123',
    gallons_requested: 500,
    delivery_address: '123 Main St, Anytown, USA',
    delivery_date: '2023-07-01',
    suggested_price: 2.5,
    total_amount_due: 1250,
  },
  {
    id: 2,
    user_id: 'def456',
    gallons_requested: 300,
    delivery_address: '456 First Ave, Othertown, USA',
    delivery_date: '2023-08-15',
    suggested_price: 2.7,
    total_amount_due: 810,
  },
  {
    id: 3,
    user_id: 'abc123',
    gallons_requested: 600,
    delivery_address: '789 Elm St, Somewhere, USA',
    delivery_date: '2023-05-17',
    suggested_price: 3.0,
    total_amount_due: 1800,
  },
  {
    id: 4,
    user_id: 'abc123',
    gallons_requested: 400,
    delivery_address: '321 Oak St, Elsewhere, USA',
    delivery_date: '2023-06-30',
    suggested_price: 2.6,
    total_amount_due: 1040,
  },
  {
    id: 5,
    user_id: 'abc123',
    gallons_requested: 550,
    delivery_address: '654 Maple Ave, Thisplace, USA',
    delivery_date: '2023-07-20',
    suggested_price: 2.8,
    total_amount_due: 1540,
  },
  {
    id: 6,
    user_id: 'abc123',
    gallons_requested: 450,
    delivery_address: '987 Pine St, Thatplace, USA',
    delivery_date: '2023-08-05',
    suggested_price: 2.9,
    total_amount_due: 1305,
  },
  {
    id: 7,
    user_id: 'abc123',
    gallons_requested: 700,
    delivery_address: '741 Spruce Blvd, Here, USA',
    delivery_date: '2023-09-15',
    suggested_price: 3.1,
    total_amount_due: 2170,
  },
  {
    id: 8,
    user_id: 'abc123',
    gallons_requested: 650,
    delivery_address: '852 Cedar Ln, There, USA',
    delivery_date: '2023-10-01',
    suggested_price: 3.2,
    total_amount_due: 2080,
  },
  {
    id: 9,
    user_id: 'abc123',
    gallons_requested: 600,
    delivery_address: '963 Walnut Dr, Nowhere, USA',
    delivery_date: '2023-11-20',
    suggested_price: 3.3,
    total_amount_due: 1980,
  },
  {
    id: 10,
    user_id: 'abc123',
    gallons_requested: 750,
    delivery_address: '1234 Birch Rd, Everywhere, USA',
    delivery_date: '2023-12-15',
    suggested_price: 3.4,
    total_amount_due: 2550,
  },
  {
    id: 11,
    user_id: 'abc123',
    gallons_requested: 800,
    delivery_address: '5678 Hickory Ct, Anyplace, USA',
    delivery_date: '2024-01-10',
    suggested_price: 3.5,
    total_amount_due: 2800,
  },
  {
    id: 12,
    user_id: 'abc123',
    gallons_requested: 850,
    delivery_address: '9012 Poplar Ave, Someplace, USA',
    delivery_date: '2024-02-01',
    suggested_price: 3.6,
    total_amount_due: 3060
  }
];

const loggedUserId = 'abc123'; // Simulated logged-in user ID

const FuelQuoteHistoryPage = () => {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  // Filter the data based on the logged-in user ID
  const filteredData = useMemo(() => sampleData.filter(item => item.user_id === loggedUserId), []);

  const columns = useMemo(
    () => [
      {
        Header: 'Order #',
        accessor: 'id',
      },
      {
        Header: 'Gallons Requested',
        accessor: 'gallons_requested',
      },
      {
        Header: 'Delivery Address',
        accessor: 'delivery_address',
      },
      {
        Header: 'Delivery Date',
        accessor: 'delivery_date',
      },
      {
        Header: 'Suggested Price',
        accessor: 'suggested_price',
      },
      {
        Header: 'Total Amount Due',
        accessor: 'total_amount_due',
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
      data: filteredData, // Use the filtered data instead of sampleData
      initialState: { pageIndex: 0 },
    },
    useSortBy,
    usePagination
  );

/*
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
    gotoPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
    setPageSize(parseInt(event.target.value, 10));
  };
*/
  const handleChangePage = (event, newPage) => {
    gotoPage(newPage);
  };

  /*
  const handleChangeRowsPerPage = event => {
    const newPageSize = parseInt(event.target.value, 10);
    setPageSize(newPageSize);
  };
*/
const handleChangeRowsPerPage = event => {
  const newPageSize = parseInt(event.target.value, 10);
  setRowsPerPage(newPageSize);
  setPageSize(newPageSize);
  setPage(0); // Reset your own page state
  gotoPage(0); // Reset React Table's internal page state
};

  return (
    <div>
      <h1 className="FormTitle mb-6">Fuel Quote History</h1>
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
      onClick={() => handleChangePage(null, 0)}
      disabled={page === 0}
      className="TablePaginationButton">
      {'<<'}
    </button>
    <button
      onClick={() => handleChangePage(null, page - 1)}
      disabled={!gotoPage || !canPreviousPage}
      className="TablePaginationButton"
    >
      {'<'}
    </button>
    <button
      onClick={() => handleChangePage(null, page + 1)}
      disabled={!gotoPage || !canNextPage}
      className="TablePaginationButton"
    >
      {'>'}
    </button>
    <button
      onClick={() => handleChangePage(null, pageCount - 1)}
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
                  handleChangePage(null, newPage);
                }}
                className="TablePaginationInput PageInput"
              />
              of {pageCount}
          </span>
        </div>
      </div>
    </div>
  );
};

export default FuelQuoteHistoryPage;
