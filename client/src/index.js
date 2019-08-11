import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import {applyMiddleware, compose, createStore, dispatch} from 'redux';
import { AppContainer } from 'react-hot-loader';
import createSagaMiddleware from 'redux-saga';
import logger from 'redux-logger'
import { App } from './components/App';
import combineReducer from './reducers';
import rootSaga from "./sagas";

const initialState = {};

const sagaMiddleware = createSagaMiddleware();
const middleware = applyMiddleware(sagaMiddleware, logger);

const store = createStore(combineReducer, initialState, middleware);
sagaMiddleware.run(rootSaga);

const render = Component => ReactDOM.render(
    <Provider store={{}}>
        <AppContainer>
            <Component/>
        </AppContainer>
    </Provider>,
    document.getElementById('root'),
);

render(() => <App />);
