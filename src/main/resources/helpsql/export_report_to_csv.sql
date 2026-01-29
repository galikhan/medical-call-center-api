\COPY (SELECT * FROM  view_25_year_complaint) TO '/home/gali/complaint.csv' CSV HEADER;
\COPY (SELECT * FROM  view_25_year_proposal) TO '/home/gali/proposal.csv' CSV HEADER;
\COPY (SELECT * FROM  view_25_year_thanks) TO '/home/gali/thanks.csv' CSV HEADER;