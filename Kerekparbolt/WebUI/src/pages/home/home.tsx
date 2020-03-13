import * as React from "react";
import { Connected } from "./../../lib/store/connected.mixin";
import { RouteComponentProps } from "react-router";
import { AppStore } from "./../../lib/appStore";
import { StorageService } from "./../../services/client/storage.service";
import { Theme, createStyles, withStyles, WithStyles, TextField, Typography, Button } from "@material-ui/core"
import withRoot from "./../../withRoot";
import { CustomColors } from "./../../style/colors";
import { LocalImages } from "./../../staticFiles/images";
import { Validation } from "./../../validators";
import { WebAPI } from "./../../services/webAPI";
import { LoginRequest } from "./../../services/client/securityService";
import { StorageKeys } from "./../../settings/constats";
import FooterComponent from "./../footer/footer";
import { Routes } from "../../routing/urls";

const styles = (theme: Theme) =>
  createStyles
  ({
    container:
    {
      display: "flex",
      flexGrow: 1,
      flexDirection: "column",
      backgroundColor: CustomColors.background,
      minHeight: "100vh",
      color: CustomColors.font
    },
    content:
    {
      display: "flex",
      flexGrow: 1,
      flexDirection: "row"
    },
    logoContainer:
    {
      display: "flex",
      alignItems: "center",
      justifyContent: "center"
    },
    loginContainer:
    {
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      flexDirection: "column",
      width: "100%"
    },
    bottom:
    {
      padding: 10,
      fontSize: 50,
      color: CustomColors.font,
      backgroundColor: CustomColors.background,
      display: "flex",
      alignItems: "center",
      justifyContent: "center"
    },
    textField:
    {
      width: "90%"
    },
    textFieldLabel:
    {
      color : `${CustomColors.darkerFont} !important`,
    },
    textFieldOutlinedInput:
    {
      "&$cssFocused $notchedOutline":
      {
        borderColor: `${CustomColors.darkerFont} !important`,
      }
    },
    textFieldFocused:
    {
      color: "orange !important"
    },
    textFieldNotchedOutline:
    {
      borderWidth: "1px",
      borderColor: CustomColors.darkerFont + "!important"
    },
    typography:
    {
      color: CustomColors.font + "!important"
    }
  });

interface IState
{
  email: string;
  password: string;
}

interface IProps
{}

class Home extends Connected<typeof React.Component, IProps & WithStyles<typeof styles> & RouteComponentProps<{}>, IState, AppStore>(React.Component)
{
    storageService: StorageService = new StorageService();

    constructor(props: IProps & WithStyles<typeof styles> & RouteComponentProps<{}>)
    {
        super(props);

        this.state =
        {
          email : "",
          password: ""
        }
    }

    componentWillMount()
    {
      const storage: StorageService = new StorageService();

      const token: string | undefined = storage.read<string>(StorageKeys.JWT);

      if (token)
      {
        this.props.history.push(Routes.Products);
      }
    }

    isFormFilled = (): boolean =>
    {
      return this.state.email.length > 0 &&
             this.state.password.length > 0 &&
             Validation.IsEmail(this.state.email);
    }

    onTextChanged = (e: React.ChangeEvent<HTMLInputElement>): void =>
    {
      e.preventDefault();
      this.setState
      ({
          [e.target.name]: e.target.value
      });
    }

    onLoginClickHandler = async (): Promise<void> =>
    {
      const data: LoginRequest =
      {
        email: this.state.email,
        jelszo: this.state.password
      };

      const token = await WebAPI.Security.login(data)
                                         .then(x => x)
                                         .catch();

      if (token)
      {
        WebAPI.setToken(token.Token!)
        this.props.history.push(Routes.Products);
      }
    }

    render()
    {
      const css = this.props.classes;

      const loginButton = this.isFormFilled() ?
      <Button variant="contained" color="primary"
        onClick={this.onLoginClickHandler}>
        BELÉPÉS
      </Button> :
      <Button variant="contained" disabled>
        BELÉPÉS
      </Button>

      const Body = () =>
        <div className={css.container}>
          <div className={css.content}>
            <div className={css.logoContainer}>
              <img src={LocalImages.images("./bringawebshop.jpg")} />
            </div>
            <div className={css.loginContainer}>
              <Typography variant="h5"
                            gutterBottom
                            className={css.typography}>
                  BEJELENTKEZÉS
              </Typography>
              <Typography variant="overline"
                          gutterBottom
                          className={css.typography}>
                EMAIL
              </Typography>
              <TextField InputLabelProps={{
                          classes: {
                            root: css.textFieldLabel,
                            focused: css.textFieldFocused
                          }
                        }}
                        InputProps={{
                          classes: {
                            root: css.textFieldOutlinedInput,
                            focused: css.textFieldFocused,
                            notchedOutline: css.textFieldNotchedOutline,
                            input: css.textFieldFocused
                          },
                        }}
                        className={css.textField}
                        name="email"
                        id="outlined-basic"
                        label="Email"
                        variant="outlined"
                        onChange={this.onTextChanged}/>

              <Typography variant="overline"
                          gutterBottom
                          className={css.typography}>
                JELSZÓ
              </Typography>
              <TextField InputLabelProps={{
                            classes: {
                              root: css.textFieldLabel,
                              focused: css.textFieldFocused
                            }
                          }}
                          InputProps={{
                            classes: {
                              root: css.textFieldOutlinedInput,
                              focused: css.textFieldFocused,
                              notchedOutline: css.textFieldNotchedOutline,
                              input: css.textFieldFocused
                            },
                          }}
                          className={css.textField}
                         name="password"
                         id="outlined-basic"
                         label="Jelszó"
                         variant="outlined"
                         type="password"
                         onChange={this.onTextChanged}/>
              {loginButton}
            </div>
          </div>
          <div className={css.bottom}>
            <div>Tekerj a mi kerékpárjainkal a zöldebb jövőért!</div>
          </div>
          <FooterComponent />
         </div>
      return Body();
  }
}

const HomePage = withRoot(withStyles(styles)(Home));
export default HomePage;