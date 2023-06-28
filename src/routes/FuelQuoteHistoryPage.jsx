import React, { useMemo, useState } from 'react';
import { useTable, useSortBy, usePagination } from 'react-table';
import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TablePagination, TableFooter } from '@mui/material';

// sample data to represent what you would fetch from the backend
const sampleData = [
  { id: 1,
    user_id: 'abc123',
    gallons_requested: 500,
    delivery_date: '2023-07-01',
    suggested_price: 2.5,
    total_amount_due: 1250,
  },
  {
    id: 2,
    user_id: 'def456',
    gallons_requested: 300,
    delivery_date: '2023-08-15',
    suggested_price: 2.7,
    total_amount_due: 810,
  },
  { id: 3,
    user_id: 'abc123',
    gallons_requested: 600,
    delivery_date: '2023-05-17',
    suggested_price: 3.0,
    total_amount_due: 1100,
  },
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
        Header: 'ID',
        accessor: 'id',
      },
      {
        Header: 'User ID',
        accessor: 'user_id',
      },
      {
        Header: 'Gallons Requested',
        accessor: 'gallons_requested',
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
    canPreviousPage,
    canNextPage,
    pageCount,
    gotoPage,
    nextPage,
    previousPage,
    setPageSize,
    state: { pageIndex, pageSize },
  } = useTable(
    {
      columns,
      data: filteredData, // Use the filtered data instead of sampleData
      initialState: { pageIndex: 0 },
    },
    useSortBy,
    usePagination
  );

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
    gotoPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
    setPageSize(parseInt(event.target.value, 10));
  };

  return (
    <div>
      <h1>Fuel Quote History</h1>
    <Paper>
      <TableContainer>
        <Table {...getTableProps()}>
          <TableHead>
            {headerGroups.map((headerGroup, index) => (
              <TableRow {...headerGroup.getHeaderGroupProps()} key={index}>
                {headerGroup.headers.map(column => (
                  <TableCell {...column.getHeaderProps(column.getSortByToggleProps())} key={column.id}>
                      <div>
                        {column.render('Header')}
                        {/* Add a sort direction indicator */}
                        <span>
                          {column.isSorted ? (column.isSortedDesc ? ' 🔽' : ' 🔼') : ''}
                        </span>
                      </div>
                  </TableCell>
                ))}
              </TableRow>
            ))}
          </TableHead>
        <TableBody {...getTableBodyProps()}>
          {tablePage.map((row, rowIndex) => {
            prepareRow(row);
            return (
              <TableRow {...row.getRowProps()} key={rowIndex}>
                {row.cells.map((cell, cellIndex) => (
                  <TableCell {...cell.getCellProps()} key={cellIndex}>{cell.render('Cell')}</TableCell>
                ))}
              </TableRow>
            );
          })}
        </TableBody>
      <TableFooter>
      <TableRow>
        <TablePagination
          rowsPerPageOptions={[10, 25, 100]}
          component="td"
          count={sampleData.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </TableRow>
    </TableFooter>
        </Table>
      </TableContainer>
      </Paper>
      </div>
  );
};

export default FuelQuoteHistoryPage;