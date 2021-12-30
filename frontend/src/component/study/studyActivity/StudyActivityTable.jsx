import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import style from "../../../css/study/study_workJournal/study_workJournal.module.css";
import styled from "styled-components";

const Td = styled.td`
  text-align: center;
  font-size: 0.9em;
`;

class StudyActivityTable extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__table-wrap"]}>
          <table className={style.main__table}>
            <colgroup>
              <col className={style.no} />
              <col className={style.title} />
              <col className={style.writer} />
              <col className={style.date} />
              <col className={style.view} />
            </colgroup>
            <thead>
              <tr>
                <th>회차</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일자</th>
                <th>조회수</th>
              </tr>
            </thead>
            <tbody>
              <tr style={{ textAlign: "center" }}>
                <Td>1</Td>
                <Td>
                  <Link to="/">스터디 1주차</Link>
                </Td>
                <Td>6기 최부엉</Td>
                <Td>2021.07.05</Td>
                <Td>20</Td>
              </tr>
              <tr style={{ textAlign: "center" }}>
                <Td>2</Td>
                <Td>
                  <Link to="/">스터디 2주차</Link>
                </Td>
                <Td>6기 최부엉</Td>
                <Td>2021.07.03</Td>
                <Td>13</Td>
              </tr>
              <tr style={{ textAlign: "center" }}>
                <Td>3</Td>
                <Td>
                  <Link to="/">스터디 3주차</Link>
                </Td>
                <Td>6기 최부엉</Td>
                <Td>2021.07.01</Td>
                <Td>3</Td>
              </tr>
            </tbody>
          </table>
        </div>
      </React.Fragment>
    );
  }
}

export default StudyActivityTable;
