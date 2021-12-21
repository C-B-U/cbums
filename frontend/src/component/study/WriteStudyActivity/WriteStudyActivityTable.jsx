import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalWriting/WriteStudyActivity.module.css";
import WriteStudyActivityButton from "./WriteStudyActivityButton";
import styled from 'styled-components';
import WriteStudyActivityAttend from "./WriteStudyActivityAttend";

const Td = styled.td`
  border: 1px solid #444444;
  padding: 10px;
`;
const Th = styled.th`
  border: 1px solid #444444;
  padding: 10px;
`;
const Input = styled.input`
  margin: 0.5em;
`;

class WriteStudyActivityTable extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__writing-wrap"]}>
              <form className={style["main__writing-form"]}>
                <table className={style["main__writing-table"]}>
                  <tr>
                    <Th id="title">제목</Th>
                    <Td>
                      <Input
                        type="text"
                        name="study-title"
                        autocomplete="off"
                        style={{ width: "95%" }}
                      />
                    </Td>
                  </tr>
                  <tr>
                    <Th>참여인원</Th>
                    {/*팀원들 출석/결석/지각 여부*/}
                    <Td>
                      <ul className={style["main__writing-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <WriteStudyActivityAttend name="mem1" />
                            6기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__writing-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <WriteStudyActivityAttend name="mem2" />
                            7기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul
                        className={
                          style["mNamea{style[in__writing-table-checkbox-ul"]
                        }
                      >
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <WriteStudyActivityAttend name="mem3" />
                            8기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__writing-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <WriteStudyActivityAttend name="mem4" />
                            9기 김부엉
                          </label>
                        </li>
                      </ul>
                    </Td>
                  </tr>
                  <tr>
                    <Th>진행날짜</Th>
                    <Td>
                      <Input type="date" name="study-duration" />
                    </Td>
                  </tr>
                  <tr>
                    <Th>세부내용</Th>
                    <Td>
                      <textarea
                        name="study-explanation"
                        style={{
                          resize: "none",
                          width: "95%",
                          height: "130px",
                        }}
                        required
                        wrap="hard"
                      />
                    </Td>
                  </tr>
                  <tr>
                    <Th>활동사진</Th>
                    <Td>
                      <Input
                        type="file"
                        name="study-image"
                        id="study-image"
                        multiple
                      />
                      <div id="image_container"></div>
                    </Td>
                  </tr>
                  <tr>
                    <Th>숙제여부</Th>
                    <Td>
                      <ul className={style["main__writing-table-checkbox-ul"]}>
                        {/* 팀원들 숙제 여부*/}
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <Input type="checkbox" name="homework-checkbox" />
                            9기 김김김
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__writing-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <Input type="checkbox" name="homework-checkbox" />
                            5기 김김김
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__writing-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <Input type="checkbox" name="homework-checkbox" />
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__writing-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__writing-table-checkbox"]}
                          >
                            <Input type="checkbox" name="homework-checkbox" />
                          </label>
                        </li>
                      </ul>
                    </Td>
                  </tr>
                </table>
                <WriteStudyActivityButton />
              </form>
            </div>
          </React.Fragment>
        );
    }
}

export default WriteStudyActivityTable;
