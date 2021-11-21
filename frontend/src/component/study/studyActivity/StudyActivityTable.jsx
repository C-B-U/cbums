import React, { PureComponent } from "react";
import { Link } from "react-router-dom";
import style from "../../../css/study/study_workJournal/study_workJournal.module.css";

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
              <tr>
                <td>1</td>
                <Link>
                  <td>스터디 1주차</td>
                </Link>
                <td>6기 최부엉</td>
                <td>2021.07.05</td>
                <td>20</td>
              </tr>
              <tr>
                <td>2</td>
                <Link>
                  <td>스터디 2주차</td>
                </Link>
                <td>6기 최부엉</td>
                <td>2021.07.03</td>
                <td>13</td>
              </tr>
              <tr>
                <td>3</td>
                <Link>
                  <td>스터디 3주차</td>
                </Link>
                <td>6기 최부엉</td>
                <td>2021.07.01</td>
                <td>3</td>
              </tr>
            </tbody>
          </table>
        </div>
      </React.Fragment>
    );
  }
}

export default StudyActivityTable;
