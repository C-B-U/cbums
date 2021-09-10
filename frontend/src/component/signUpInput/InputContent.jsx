import React, { PureComponent } from "react";
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";

class InputContent extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="article__boxList">
          <form action="#" method="POST">
            <ul className="boxList__infor">
              <li>
                <label>이름</label>
                <input type="text" placeholder="이름 입력" name="name" />
              </li>
              <li>
                <label>학과</label>
                <input
                  type="number"
                  placeholder="학번 입력"
                  name="schoolNumber"
                />
              </li>
              <li>
                <label>전화번호</label>
                <input
                  type="number"
                  placeholder="전화번호 입력"
                  name="phoneNumber"
                />
              </li>
              <li>
                <input
                  type="submit"
                  style={{ cursor: "pointer" }}
                  className="boxList__infor--submit"
                />
              </li>
            </ul>
          </form>
          <p className="boxList__controls">
            <span className="prev">
              <FaArrowLeft />
            </span>
            <span className="next">
              <FaArrowRight />
            </span>
          </p>
        </div>
      </React.Fragment>
    );
  }
}

export default InputContent;
/*
 
 */
